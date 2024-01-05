#lang racket

(cons 1 2)

(cons 1 (cons 2 (cons 3 empty)))

(cons "My epic string"
      (cons 164
            (cons (cons 1 (cons 2 (cons 3 '())))
                  '())))

(list "hi" 2 (list 1 2 3))

(append '("Hello") '(12) '(1 2 3))

