DECLARE @minValue DECIMAL(18, 2) = 0.00;  -- Set your min value
DECLARE @maxValue DECIMAL(18, 2) = 100000.00; -- Set your max value
DECLARE @amount DECIMAL(18, 2) = ROUND(@minValue + (RAND(CHECKSUM(NEWID())) * (@maxValue - @minValue)), 2);

INSERT INTO DBO.revenue (customer_id, bank_account_id, amount)
SELECT :customerId, :bankAccountId, @amount