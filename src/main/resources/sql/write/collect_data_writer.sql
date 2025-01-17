MERGE INTO DBO.external_data
USING (VALUES (:id,
               :ssn,
               :bankId,
               :bankName,
               :bankAccountNumber,
               :bankAccountBalance,
               :revenueAmount,
               :loanId,
               :loan,
               :payed,
               :debt,
               :delay,
               :createdAt))
    AS source (
               id,
               ssn,
               bankId,
               bankName,
               bankAccountNumber,
               bankAccountBalance,
               revenueAmount,
               loanId,
               loan,
               payed,
               debt,
               delay,
               createdAt
        )
ON DBO.external_data.id = source.id
WHEN MATCHED THEN
    UPDATE
    SET DBO.external_data.ssn                  = source.ssn,
        DBO.external_data.bank_id              = source.bankId,
        DBO.external_data.bank_name            = source.bankName,
        DBO.external_data.bank_account_number  = source.bankAccountNumber,
        DBO.external_data.bank_account_balance = source.bankAccountBalance,
        DBO.external_data.revenue_amount       = source.revenueAmount,
        DBO.external_data.loan_id              = source.loanId,
        DBO.external_data.loan                 = source.loan,
        DBO.external_data.payed                = source.payed,
        DBO.external_data.debt                 = source.debt,
        DBO.external_data.delay                = source.delay,
        DBO.external_data.created_at           = source.createdAt
WHEN NOT MATCHED THEN
    INSERT (ssn,
            bank_id,
            bank_name,
            bank_account_number,
            bank_account_balance,
            revenue_amount,
            loan_id,
            loan,
            payed,
            debt,
            delay,
            created_at)
    VALUES (source.ssn,
            source.bankId,
            source.bankName,
            source.bankAccountNumber,
            source.bankAccountBalance,
            source.revenueAmount,
            source.loanId,
            source.loan,
            source.payed,
            source.debt,
            source.delay,
            source.createdAt);