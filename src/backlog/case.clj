(ns backlog.case)

(defn normalized
  [s]
  (clojure.string/join
    "-"
    (map clojure.string/lower-case
         (clojure.string/split s #"(?<=[a-z])(?=[A-Z])|_"))))

(defn camel
  [s]
  (let [[w & ws] (clojure.string/split (normalized s) #"-")]
    (apply str w (map clojure.string/capitalize ws))))

(defn pascal
  [s]
  (apply str (map clojure.string/capitalize
                  (clojure.string/split (normalized s) #"-"))))

(defn snake
  [s]
  (clojure.string/join
    "_"
    (clojure.string/split (normalized s) #"-")))

(defn normalized-keyword
  [k]
  (keyword (normalized (name k))))

(defn camel-keyword
  [k]
  (keyword (camel (name k))))

(defn pascal-keyword
  [k]
  (keyword (pascal (name k))))

(defn snake-keyword
  [k]
  (keyword (snake (name k))))

