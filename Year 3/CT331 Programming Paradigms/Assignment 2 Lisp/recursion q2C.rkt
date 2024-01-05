#lang racket

(provide cout_top_level)

(define (cout_top_level lst)
  (if (null? lst)
      0
      (+ 1 (cout_top_level (cdr lst)))
  )
)