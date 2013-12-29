#include <SPI.h> // needed in Arduino 0019 or later
#include <Ethernet.h>
#include <Twitter.h>

// The includion of EthernetDNS is not needed in Arduino IDE 1.0 or later.
// Please uncomment below in Arduino IDE 0022 or earlier.
//#include <EthernetDNS.h>


// Ethernet Shield Settings
byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };

// If you don't specify the IP address, DHCP is used(only in Arduino 1.0 or later).
byte ip[] = { 192, 168, 2, 250 };

// Your Token to Tweet (get it from http://arduino-tweet.appspot.com/)
Twitter twitter;

// Message to post
char msg[] = "Hello World! Diretamente do Arduino!";

void setup(){
  delay(1000);
  Ethernet.begin(mac, ip);
  // or you can use DHCP for autoomatic IP address configuration.
  // Ethernet.begin(mac);
  Serial.begin(9600);
  
  
}

String serialData;
int id;
char SEPARATOR = (char) 9679;
int TOKEN_ID = 1;
int TWEET_ID = 2;

void loop(){
  char c;
  while(Serial.available()) {
      c = Serial.read();
      if(c == SEPARATOR){
        id = c-48;
        serialData = "";
      }
      serialData.concat(c);
      Serial.write(c);
  }
  
  doSomething(id);
}

void setToken(String token){
   twitter(token);
}

void doSomething(int action){
  switch(action){
    case TOKEN_ID:
       setToken(serialData);
    case TWEET_ID:
       Serial.println("Connecting ...");
        if (twitter.post(serialData)) {
          // Specify &Serial to output received response to Serial.
          // If no output is required, you can just omit the argument, e.g.
          // int status = twitter.wait();
          int status = twitter.wait(&Serial);
          if (status == 200) {
            Serial.println("OK.");
          } else {
            Serial.print("Failed: code ");
            Serial.println(status);
          }
        } else {
          Serial.println("Connection failed.");
        }
  }
}
