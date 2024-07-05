MERGE INTO DBO.calculus
USING (VALUES (:id,
               :loanId,
               :debt,
               :feePercentage,
               :fee,
               :calculatedDebt))
    AS source (id,
               loanId,
               debt,
               feePercentage,
               fee,
               calculatedDebt)
ON DBO.calculus.id = source.id
WHEN MATCHED THEN
    UPDATE
    SET DBO.calculus.loan_id         = source.loanId,
        DBO.calculus.debt            = source.debt,
        DBO.calculus.fee_percentage  = source.feePercentage,
        DBO.calculus.fee             = source.fee,
        DBO.calculus.calculated_debt = source.calculatedDebt
WHEN NOT MATCHED THEN
    INSERT (loan_id,
            debt,
            fee_percentage,
            fee,
            calculated_debt)
    VALUES (source.loanId,
            source.debt,
            source.feePercentage,
            source.fee,
            source.calculatedDebt);