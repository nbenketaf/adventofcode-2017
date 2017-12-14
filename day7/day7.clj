(def line-regex #"(\w+)\s\((\d+)\)\s?-?>?\s?(.*)")

(defn parse [filename]
  (->> filename
       slurp
       clojure.string/split-lines
       (map #(re-matches line-regex %))))

(defn solve [input]
  (let [[holding holded] (->> input
                              (filter (comp not-empty last))
                              (map (juxt second last))
                              (reduce (fn [[holding holded] line]
                                        [(conj holding (first line)) (->> (clojure.string/split (last line) #",")
                                                                          (map clojure.string/trim)
                                                                          (apply conj holded))])
                                      [#{} #{}]))]
    (first (clojure.set/difference holding holded))))
