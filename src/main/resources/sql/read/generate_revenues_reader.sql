SELECT C.id AS CUSTOMER_ID,
       BA.id AS BANK_ACCOUNT_ID
FROM customer C
OUTER APPLY (SELECT BA.id
             FROM bank_account BA
             WHERE BA.customer_id = C.id) BA
