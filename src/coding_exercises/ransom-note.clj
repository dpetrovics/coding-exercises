(ns ransom-note 
  (:require [clojure.string :as str]))

(def problem-statement
  "A ransom note can be formed by cutting words out of a magazine to form a new sentence. How would you figure out if a ransom note (string) can be formed from a given magazine (string)?")

(def sample-magazine
  "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).")

(def sample-note
  "The web is readable over the desktop")

(defn can-make-note?
  ^{:doc "This function checks to see whether a given magazine (string) has all the words necessary to make the give ransom note (string). Outputs whether each word in the note is OK or if there is not enough of it in the magazine to make the note."}
  [note magazine]
  (let [note-map (frequencies
                  (re-seq #"\w+" note))
        magazine-map (frequencies
                      (re-seq #"\w+" magazine))]
    (for [[k v] note-map]
      (if (>= (get magazine-map k 0) (get note-map k 0))
        (str "'" k "'" " is OK.")
        (str "Not enough " "'" k "'" "!")))))

(defn can-make-note-from-file?
  ^{:doc "This function calls can-make-note? by reading the magazine in from an input file"}
  [note magazine-file]
  (can-make-note?
   note
   (slurp magazine-file)))

