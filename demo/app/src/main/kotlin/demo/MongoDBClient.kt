import org.bson.types.ObjectId
import org.litote.kmongo.*

data class User(
  val _id: ObjectId,
  val user: String,
  val password: String,
  val n: Int,
)

class MongoDBClient {
  private val client = KMongo.createClient()
  private val database = client.getDatabase("users")
  private val collection = database.getCollection<User>()

  fun insertData(data: User) {
    collection.insertOne(data)
  }

  fun retrieveData(): List<User> {
    return collection.find().toList()
  }

  fun deleteData(data: User) {
    collection.deleteOneById(data._id)
  }
}
