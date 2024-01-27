package demo

import MongoDBClient
import User
import org.bson.types.ObjectId

fun main() {
  val mongoDBClient = MongoDBClient()

  for (i in 1..10) {
    val newUser = User(ObjectId(), getRandomString(10), getRandomString(10), getRandomInt(2))
    insertData(mongoDBClient, newUser)
  }

  // val users = retriverData(mongoDBClient)
  // users.forEach { println(it) }
}

fun insertData(
  client: MongoDBClient,
  data: User,
) {
  try {
    client.insertData(data)
  } catch (e: Exception) {
    print("Erro ao inserir dados: ${e.message}")
  }
}

fun retriverData(client: MongoDBClient): List<User> {
  try {
    return client.retrieveData()
  } catch (e: Exception) {
    print("Erro ao recuperar dados: ${e.message}")
    return listOf()
  }
}

fun getRandomString(length: Int): String {
  val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
  return (1..length)
    .map { allowedChars.random() }
    .joinToString("")
}

fun getRandomInt(length: Int): Int {
  val allowedChars = ('0'..'9')
  return (1..length)
    .map { allowedChars.random() }
    .joinToString("")
    .toInt()
}
