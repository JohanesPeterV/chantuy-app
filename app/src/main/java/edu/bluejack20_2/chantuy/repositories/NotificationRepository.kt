
package edu.bluejack20_2.chantuy.repositories

import com.google.android.gms.tasks.Task
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.local.QueryResult
import edu.bluejack20_2.chantuy.models.CommentNotification
import edu.bluejack20_2.chantuy.models.Notification

class NotificationRepository {
    companion object {
        private val COLLECTION_NAME = "notifications"

        fun addNotif(commentId: String, curhatPosterUserId: String, callback: () -> Unit) {
            val db = FirebaseFirestore.getInstance()

            val notif = CommentNotification("", curhatPosterUserId, commentId)
            db.collection(COLLECTION_NAME).add(notif)
                .addOnSuccessListener {
                    callback()
                }
        }
        //curhatPosterUserId
        fun getNotif(userId: String, lim: Long, callback: (List<Notification>) -> Unit) {
            val db = FirebaseFirestore.getInstance()
            db.collection(COLLECTION_NAME).orderBy("createdAt", Query.Direction.DESCENDING).whereEqualTo("curhatPosterUserId", userId).limit(lim)
                .get().addOnSuccessListener {
                    val notif = mutableListOf<Notification>()
                    for (notifSnapshot in it) {
                        notif.add(notifSnapshot.toObject(Notification::class.java))
                    }
                    callback(notif.toList())
                }
        }

        fun getNotifCount(callback: (Int) -> Unit) {
            val db = FirebaseFirestore.getInstance()
            db.collection(COLLECTION_NAME).whereNotEqualTo("curhatPosterUserId",FirebaseAuth.getInstance().currentUser.uid).get()
                .addOnSuccessListener {
                    callback(it.size())
                }
        }

        fun deleteByCommentId(commentId: String, callback: () -> Unit) {
            val db = FirebaseFirestore.getInstance()
            db.collection(COLLECTION_NAME).whereEqualTo("commentId", commentId).get()
                .addOnSuccessListener {
                    it.documents.forEach {
                        it.reference.delete()
                    }
                    callback()
                }
        }
    }

}