SELECT C.id AS ID_CUSTOMER,
       BA.id AS ID_BANK_ACCOUNT
FROM customer C
OUTER APPLY (SELECT BA.id
             FROM bank_account BA
             WHERE BA.customer_id = C.id) BA
