(define snlist-recur
    (lambda (base-value proc list-proc)
            (letrec
                ([helper
                     (lambda (ls)
                        (if (null? ls)
                            base-value
                            (if (list? (car ls))
                                (list-proc (helper (car ls)) (helper (cdr ls)))
                                (proc (car ls)
                                        (helper (cdr ls))))))])
            helper
        )
    )
)

(define sn-list-sum
    (lambda (ls)
        ((snlist-recur 0 + +) ls)
    )
)

(define sn-list-map
    (lambda (func ls)
        (define funky-curry
            (lambda (x base)
                (cons (func x) base)
        ))
        ((snlist-recur '() funky-curry cons) ls)
    )
)

(define sn-list-paren-count
    (lambda (ls)
        (define go-down-list (lambda (x y) (+ x y)))
        ((snlist-recur 2
                       (lambda (x y) 2)
                       go-down-list)
         ls)
    )
)

(define sn-list-reverse
    (lambda (ls)
        ((snlist-recur '()
                       (lambda (x y) (reverse (cons x y)))
                       (lambda (x y) (cons x y)))
         ls)
    )
)

(define sn-list-occur
    (lambda (search ls)
        ((snlist-recur 0
                       (lambda (x y) (+ (if (eq? x search) 1 0) y))
                       +) ls)
    )
)

(define sn-list-depth
    (lambda (ls)
        ((snlist-recur 1 (lambda (x y) + x y) (lambda (x y) (max (+ 1 x) y))) ls)
    )
)

(define bt-recur
    (lambda (base-value proc)
        (letrec
            ([helper
                 (lambda (ls)
                     (if (null? ls)
                         base-value
                         (if (number? base-value)
                             (if (not (list? ls))
                                 ls
                                 (proc (helper (cadr ls)) (helper (caddr ls)))
                                )
                             (if (list? ls)
                                 (proc
                                     (if (list? (cadr ls))
                                         (helper (cadr ls))
                                         '()
                                     )
                                     (list (car ls))
                                     (if (list? (caddr ls))
                                          (helper (caddr ls))
                                         '()))
                                 base-value))))])
            helper)
    )
)

(define bt-sum
    (lambda (bt)
        ((bt-recur 0 +)   bt)
    )
)

(define bt-inorder
    (lambda (bt)
        ((bt-recur '() append) bt)
    )
)

(define compose
    (case-lambda
        [() (lambda (x) x)]
        [(first . rest)
         (let ([composed-rest (apply compose rest)])
             (lambda (x) (first (composed-rest x))))]))


(define (make-c...r str)
    (let ([ls (string->list str)])
        (apply compose (map (lambda (a b) (if (equal? a #\a) car cdr)) ls ls))
    )
)

(define make-slist-leaf-iterator
    (lambda (ls)
        (let* ([stack (make-stack)])
            (map (lambda (x) (stack 'push x)) ls)
            (letrec ([iter
                (lambda ()
                    (let ([item (if (stack 'empty?) #f (stack 'pop))])
                        (cond [(null? item) (iter)]
                              [(list? item) (map (lambda (x) (stack 'push x)) item) (iter)]
                              [else item])
                        ))])
                iter)
        )
    )
)

(define make-stack
    (lambda ()
        (let ([stk '()])
            (lambda (msg . args )
                (case msg ; Scheme's case is a similar to switch in some other languages.
                    [(empty?) (null? stk)]
                    [(push) (set! stk (cons (car args) stk))]
                    [(pop) (let ([top (car stk)])
                                (set! stk (cdr stk))
                               top)]
                    [else (errorf 'stack "illegal message to stack object: ~a" msg)])))))