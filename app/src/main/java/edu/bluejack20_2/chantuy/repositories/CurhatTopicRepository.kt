package edu.bluejack20_2.chantuy.repositories

import android.util.Log
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.bluejack20_2.chantuy.models.CurhatTopic

class CurhatTopicRepository {
    companion object {
        private const val COLLECTION_NAME = "topics"

        fun getAll(callback: (List<CurhatTopic>) -> Unit) {
            val db = FirebaseFirestore.getInstance()

            db.collection(COLLECTION_NAME).get()
                .addOnSuccessListener {topicDocs ->
                    val topics = mutableListOf<CurhatTopic>()
                    for (topicDoc in topicDocs) {
                        val topic = topicDoc.toObject(CurhatTopic::class.java)
                        topic.id = topicDoc.id
                        topics.add(topic)
                    }
                    callback(topics)
                }
        }

        fun get(topicId: String, callback: (CurhatTopic) -> Unit) {
            val db = FirebaseFirestore.getInstance()

            db.collection(COLLECTION_NAME).document(topicId).get()
                .addOnSuccessListener {topicDoc ->
                    callback(topicDoc.toObject(CurhatTopic::class.java)!!)
                }
        }

        fun addTopic(topicName: String ,callback: (String) -> Unit) {
            val db = FirebaseFirestore.getInstance()

            val newTopic = CurhatTopic("", topicName)
            db.collection(COLLECTION_NAME).add(newTopic)
                .addOnSuccessListener { newTopicRef ->
                    callback(newTopicRef.id)
                }
        }
    }
}