(defn parse [filename]
  (->> filename
       slurp
       str/split-lines
       (map #(str/split % #" "))))

(defn solve [f colls]
  (->> (filter #(->> (map f %)
                     (apply distinct?)) colls)
       count))

(defn day4-1 [filename]
  (let [colls (parse filename)
        res1 (solve identity colls)
        res2 (solve sort colls)]
    (println res1)
    (println res2)))

