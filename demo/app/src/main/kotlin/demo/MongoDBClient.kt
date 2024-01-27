import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection
import org.bson.types.ObjectId

data class User(
    val _id: ObjectId, // Representa o ObjectId do MongoDB
    val user: String, // Representa o campo 'user'
    val password: String, // Representa o campo 'password'
    val n : Int
)


class MongoDBClient {
  private val client = KMongo.createClient() // Conexão padrão localhost e porta 27017
  private val database = client.getDatabase("users") // Substitua com o nome do seu banco de dados

  private val collection = database.getCollection<User>()

  fun insertData(data: User) {
    collection.insertOne(data)
  }

  fun retrieveData(): List<User> {
    return collection.find().toList()
  }
}

