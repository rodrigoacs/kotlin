package demo

import MongoDBClient
import User
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
  embeddedServer(Netty, port = 8080) {
    install(ContentNegotiation) {
      gson {
      }
    }
    routing {
      get("/user") {
        val mongoDBClient = MongoDBClient()
        val users = mongoDBClient.retrieveData()
        call.respond(users)
      }

      get("/user/{user}") {
        val mongoDBClient = MongoDBClient()
        val users = mongoDBClient.retrieveData()
        val user = call.parameters["user"]
        val userFound = users.find { it.user == user }
        if (userFound != null) {
          call.respond(userFound)
        } else {
          call.respondText("User not found")
        }
      }

      post("/user") {
        val mongoDBClient = MongoDBClient()
        val newUser = call.receive<User>()

        mongoDBClient.insertData(newUser)
        call.respond("User created with ID: ${newUser._id}")
      }

      delete("/user/{user}") {
        val mongoDBClient = MongoDBClient()
        val users = mongoDBClient.retrieveData()
        val user = call.parameters["user"]
        val userFound = users.find { it.user == user }
        if (userFound != null) {
          mongoDBClient.deleteData(userFound)
          call.respondText("User deleted")
        } else {
          call.respondText("User not found")
        }
      }
    }
  }.start(wait = true)
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

fun deleteData(
  client: MongoDBClient,
  data: User,
) {
  try {
    client.deleteData(data)
  } catch (e: Exception) {
    print("Erro ao deletar dados: ${e.message}")
  }
}
