Chirpy
======

2014 Google IO Hackathon Submission
-----------------------------------

Chirpy is a twitter client and proof and concept and implementation of the unbreakable One-Time-Pad cryptography protocol that runs on Android.

Secure key sharing is implemented using Near Field Communication and hash/entropy generation is done using gyroscope/accelerometer sensors.

### What is it?
A cryptographic chat application that:
-   uses sensors to generate entropy.
-   uses Twitter to host the ciphertext.

### State of Android/Linux Random
-   `SecureRandom` is a psuedo random number generator that is seeded at
    boot
-   /dev/random and /dev/urandom are psuedo random number generator
    gathering entropy from keyboard timings, mouse timings, etc.

### Our Idea
-   Android devices have sensors!
-   We worked with accelerometer

### State of “Twitter Chats”
-   Twitter is of the most reliable and public ways to post messages.
-   It has been used to mobilize people during unrest.

### Our Idea
-   Some messages should be private!
-   We facilitate posting ciphertext to Twitter

### Future Work
-   Implement a more practical cryptographic scheme.
-   More control over saving (or not!) messages.
