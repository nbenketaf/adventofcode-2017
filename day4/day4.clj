(defn parse [filename]
  (->> filename
       slurp
       str/split-lines
       (map #(str/split % #" "))))

(defn solve [valid? colls]
  (->> (filter valid? colls)
       count))

(defn anagram [coll]
  (->> coll
       (group-by sort)
       vals
       (filter #(> (count %) 1))))

(defn day4-1 [filename]
  (let [colls (parse filename)
        res1 (solve #(apply distinct? %) colls)
        res2 (solve #(empty? (anagram %)) colls)]
    (println res1)
    (println res2)))

