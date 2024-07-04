INSERT INTO DBO.bank_account (customer_id, bank_id, account_number, balance)
SELECT :customerId, :bankId, :accountNumber, :balance