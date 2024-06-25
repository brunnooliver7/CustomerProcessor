package com.bruno.customerprocessor.service

import com.bruno.customerprocessor.entity.external.*
import com.bruno.customerprocessor.utils.BigDecimalUtils
import io.github.serpro69.kfaker.Faker
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class DataGeneratorService {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    private val logger: Logger = LoggerFactory.getLogger(DataGeneratorService::class.java)

    @Transactional
    fun generateCustomers(number: Int, batchSize: Int): List<Customer> {
        logger.info("Generating Customers")

        val faker = Faker()

        faker.unique.configuration {
            enable(faker::name)
        }

        val customers = mutableListOf<Customer>()

        for (i in 1..number) {
            val customer = Customer(
                firstName = faker.name.firstName(),
                lastName = faker.name.lastName(),
                age = faker.random.nextInt(18, 90)
            )

            entityManager.persist(customer)

            customers.add(customer)

            if (i % batchSize == 0) {
                entityManager.flush()
                entityManager.clear()
            }
        }

        return customers
    }

    @Transactional
    fun generateBanks(number: Int, batchSize: Int): List<Bank> {
        logger.info("Generating Banks")

        val faker = Faker()

        faker.unique.configuration {
            enable(faker::bank)
        }

        val banks = mutableListOf<Bank>()

        for (i in 1..number) {
            val bank = Bank(
                bankName = faker.bank.name()
            )

            entityManager.persist(bank)

            banks.add(bank)

            if (i % batchSize == 0) {
                entityManager.flush()
                entityManager.clear()
            }
        }

        return banks
    }

    @Transactional
    fun generateBankAccounts(
        customers: List<Customer>,
        banks: List<Bank>,
        batchSize: Int
    ): List<BankAccount> {
        logger.info("Generating Bank Accounts")

        val faker = Faker()

        val bankAccounts = mutableListOf<BankAccount>()

        for (customer in customers) {
            val bankAccount = BankAccount(
                customer = customer,
                bank = banks.random(),
                accountNumber = faker.finance.creditCard(""),
                balance = BigDecimalUtils.randomBetween(BigDecimal.valueOf(0), BigDecimal.valueOf(10_000))
            )

            entityManager.persist(bankAccount)

            bankAccounts.add(bankAccount)

            if (bankAccounts.size % batchSize == 0) {
                entityManager.flush()
                entityManager.clear()
            }
        }

        return bankAccounts
    }

    @Transactional
    fun generateRevenues(
        customers: List<Customer>,
        bankAccounts: List<BankAccount>,
        number: Int,
        batchSize: Int
    ): List<Revenue> {
        logger.info("Generating Revenues")

        val revenues = mutableListOf<Revenue>()

        for (i in 1..number) {
            val revenue = Revenue(
                customer = customers[i - 1],
                bankAccount = bankAccounts.find { ba -> ba.customer?.id == customers[i - 1].id },
                amount = BigDecimalUtils.randomBetween(BigDecimal.valueOf(10), BigDecimal.valueOf(1_000)),
            )

            entityManager.persist(revenue)

            revenues.add(revenue)

            if (i % batchSize == 0) {
                entityManager.flush()
                entityManager.clear()
            }
        }

        return revenues
    }

    @Transactional
    fun generateLoans(customers: List<Customer>, batchSize: Int): List<Loan> {
        logger.info("Generating Loans")

        val faker = Faker()

        val loans = mutableListOf<Loan>()

        for (i in 1..customers.size) {
            val loanAmount = BigDecimalUtils.randomBetween(BigDecimal.valueOf(1_000), BigDecimal.valueOf(10_000))
            var payedAmount = BigDecimalUtils.randomBetween(BigDecimal.valueOf(1_000), BigDecimal.valueOf(10_000))
            while (payedAmount > loanAmount) {
                payedAmount = BigDecimalUtils.randomBetween(BigDecimal.valueOf(1_000), BigDecimal.valueOf(10_000))
            }

            val loan = Loan(
                customer = customers[i - 1],
                loan = loanAmount,
                payed = payedAmount,
                debt = loanAmount - payedAmount,
                delay = faker.random.nextInt(0, 100)
            )

            entityManager.persist(loan)

            loans.add(loan)

            if (i % batchSize == 0) {
                entityManager.flush()
                entityManager.clear()
            }
        }

        return loans
    }

}
