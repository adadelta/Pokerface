# PokerFace

PokerFace is a poker hand translator, translating your poker hand (consisting of five cards) to its given name.

## Getting started

### Prerequisites

* Install the latest version of [Java](https://java.com), [Maven](https://maven.apache.org/download.html) and [Git](https://git-scm.com/downloads).

### Installing
Fetch the project from GitHub

```bash
git clone https://github.com/glfinnbogason/Pokerface.git
```

Now build and run the projects tests

```bash
mvn verify
```

### USAGE

**Input**

Create a file (e.g. hands.txt) and add the descriptions of the hands you want translated. Each description is in the form CS, where C is the name of the card (2, 3, 4, 5, 6, 7, 8, 9, T, J, Q, K, A) and S is the suit (H, D, S, C for Hearts, Diamonds, Spades and Clubs respectively).

*Example input:*

```bash
3H JS 3C 7C 5D

JH 2C JD 2H 4C

9H 9D 3S 9S 9C

9C 3H 9S 9H 3S

3H JS 3C 7C 5D
```

**Running the program**

To run the application, make sure you are in the projects directory and run

```bash
java -jar target/PokerFace-1.0.0.jar --file=<FILE-WITH-POKER-HANDS>
```
Where `<FILE-WITH-POKER-HANDS>` is the file holding the hands data, e.g. ~/myfolder/hands.txt

**Output**

The name of the hand will be one of:

* High card
* One pair
* Two pair
* Three of a kind
* Straight
* Flush
* Full house
* Four of a kind
* Straight flush
* Royal Flush


*Example output:*

```bash
3H JS 3C 7C 5D => One pair

JH 2C JD 2H 4C => Two pair

9H 9D 3S 9S 9C => Four of a kind

9C 3H 9S 9H 3S => Full house
```

## Author

**Gudlaugur Finnbogason** - me[at]gulli.dev
