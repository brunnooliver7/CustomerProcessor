SELECT ED.id             AS ID,
       C.ssn             AS SSN,
       B.bank_name       AS BANK_NAME,
       BA.account_number AS BANK_ACCOUNT_NUMBER,
       BA.balance        AS BANK_ACCOUNT_BALANCE,
       RV.amount         AS REVENUE_AMOUNT,
       L.id              AS LOAN_ID,
       L.loan            AS LOAN,
       L.payed           AS PAYED,
       L.debt            AS DEBT,
       L.delay           AS DELAY
FROM DBO.customer C
         OUTER APPLY (SELECT BA.bank_id, BA.account_number, BA.balance
                      FROM DBO.bank_account BA
                      WHERE BA.customer_id = C.id) BA
         OUTER APPLY(SELECT B.bank_name
                     FROM DBO.bank B
                     WHERE B.id = BA.bank_id) B
         OUTER APPLY (SELECT RV.amount
                      FROM DBO.revenue RV
                      WHERE RV.customer_id = C.id) RV
         CROSS APPLY (SELECT *
                      FROM DBO.loan L
                      WHERE L.customer_id = C.id) L
         OUTER APPLY (SELECT ED.id
                      FROM DBO.external_data ED
                      WHERE ED.loan_id = L.id) ED