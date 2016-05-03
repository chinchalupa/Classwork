; Problem 1
(define free-vars
    (lambda (ls)
        (remove-dups (free-vars-helper ls))))

(define remove-dups
    (lambda (e)
        (if (null? e) '()
            (cons (car e)
                  (remove-dups
                    (filter
                      (lambda (x)
                          (not (equal? x (car e))))
                              (cdr e)))))
    )
)


(define free-vars-helper
    (lambda (ls)
        (if (null? ls)
            '()
            (if (list? ls)
                (let ([first-expr (car ls)])
                    (if (is-lambda first-expr)
                        (get-all-free (cadr ls) (free-vars-helper (caddr ls)))
                        (if (list? first-expr)
                            (append (free-vars-helper first-expr) (free-vars-helper (cdr ls)))
                            (append (list first-expr) (free-vars-helper (cdr ls))))
                    )
                )
                (list ls)
            )
        )
    )
)

(define get-all-free
    (lambda (args def)
        (if (not (list? def))
            (get-all-free-helper args (list def))
            (get-all-free-helper args def)
            )))

(define get-all-free-helper
    (lambda (args lsdef)
        (if (null? args)
            lsdef
            (get-all-free-helper (cdr args) (remove (car args) lsdef))
        )
    )
)

(define is-lambda
    (lambda (ls)
        (equal? ls 'lambda)
        )
    )

(define bound-vars
    (lambda (ls)
        (if (null? ls)
            '()
            (if (list? ls)
                (let ([first-expr (car ls)])
                    (if (is-lambda first-expr)
                        (get-all-bound (cadr ls) (bound-vars (caddr ls)))
                        (if (list? first-expr)
                            (append (bound-vars first-expr) (bound-vars (cdr ls)))
                            (bound-vars (cadr ls)))
                        )
                    )
                (list ls)
            )
        )
    )
)

(define get-all-bound
    (lambda (args def)
        (if (not (list? def))
            (get-all-bound-helper args (list def))
            (get-all-bound-helper args def)
            )))

(define get-all-bound-helper
    (lambda (args lsdef)
        (apply append (map (lambda (x)
                               (if (member x args) (list x) '()))
                           lsdef))
        )
    )

(define get-non-lambdas
    (lambda (ls)
        (if (null? ls)
            '()
            (if (not (equal? 'lambda (car ls)))
                (cons (car ls) (get-non-lambdas (cdr ls)))
                (get-non-lambdas (cdr ls))
            )
        )
    )
)

(define occurs-free?
    (lambda (ltr ls)
        (if (equal? #f (member ltr (free-vars ls)))
            #f
            #t)
    )
)

(define occurs-bound?
    (lambda (ltr ls)
        (if (eq? #f (member ltr (free-vars ls)))
            #t
            #f)))