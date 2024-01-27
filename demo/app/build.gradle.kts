plugins {
  alias(libs.plugins.jvm)
  application
}

repositories {
  mavenCentral()
}

dependencies {
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
  testImplementation(libs.junit.jupiter.engine)
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
  implementation("org.litote.kmongo:kmongo:4.2.7")
  implementation(libs.guava)
  implementation("io.ktor:ktor-server-netty:1.6.7") // Substitua pela versão mais recente do Ktor
  implementation("ch.qos.logback:logback-classic:1.2.3") // Para logging
  implementation("io.ktor:ktor-server-core:1.6.7")
  implementation("io.ktor:ktor-server-host-common:1.6.7")
  implementation("io.ktor:ktor-gson:1.6.7")
}

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(11))
  }
}

application {
  mainClass.set("demo.AppKt")
}

tasks.named<Test>("test") {
  useJUnitPlatform()
}
