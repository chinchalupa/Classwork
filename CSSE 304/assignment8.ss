(define slist-map
    (lambda (proc slist)
        (map (lambda (x y)
             (if  (list? x)
                  (slist-map proc x)
                  (proc x)
             )
         ) slist slist)
    )
)

(define (reverse ls)
    (if (null? ls)
        '()
        (append (reverse (cdr ls)) (list (car ls)))
        )
    )

(define slist-reverse
    (lambda (ls)
        (if (list? ls)
            (reverse (map (lambda (x) (slist-reverse x)) ls))
            ls
        )
    )
)

(define slist-paren-count
    (lambda (ls)
        (if (list? ls)
            (if (null? ls)
                2
                (apply + 2 (map (lambda (x) (slist-paren-count x)) ls))
            )
        0)
    )
)

(define slist-depth
    (lambda (ls)
        (if (list? ls)
            (if (null? ls)
                1
                (+ 1 (apply max (map (lambda (x) (slist-depth x)) ls)))
            )
        0)
    )
)

(define slist-symbols-at-depth
    (lambda (ls ct)
        (if (eq? 1 ct)
            (filter symbol? ls)
            (apply append (map (lambda (x) (slist-symbols-at-depth x (- ct 1))) (filter list? ls)))
        )
    )
)

(define group-by-two
    (lambda (ls)
        (if (null? ls)
            '()
            (if (> (length ls) 1)
                (cons (list (car ls) (cadr ls)) (group-by-two (cddr ls)))
                (cons (list (car ls)) (group-by-two (cdr ls)))
            )
        )
    )
)

(define group-by-n
    (lambda (ls n)
        (define remainder (list-remainder ls n))
        (if (null? ls)
            '()
            (cons  (group ls n) (group-by-n remainder n))
        )
    )
)

(define list-remainder
    (lambda (ls n)
        (if (or (eq? n 0) (null? ls))
            ls
            (list-remainder (cdr ls) (- n 1))
        )
    )
)

(define group
    (lambda (ls n)
        (if (or (eq? n 0) (null? ls))
            '()
            (cons (car ls) (group (cdr ls) (- n 1)))
        )
    )
)

(define subst-leftmost
    (lambda (repl search ls check)
        (if (null? ls)
            ls
            (if (list? (car ls))
                (if (eq? (subst-leftmost repl search (car ls) check) (car ls))
                    (apply list (car ls) (subst-leftmost repl search (cdr ls) check))
                    (cons (subst-leftmost repl search (car ls) check) (cdr ls))
                )
                (if (check (car ls) search)
                    (cons repl (cdr ls))
                    (apply list (car ls) (subst-leftmost repl search (cdr ls) check))
                )
            )
        )
    )
)