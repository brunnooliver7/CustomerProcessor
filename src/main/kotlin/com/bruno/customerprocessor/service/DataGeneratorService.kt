package com.bruno.customerprocessor.service

import com.bruno.customerprocessor.entity.external.*
import com.bruno.customerprocessor.utils.BigDecimalUtils
import com.bruno.customerprocessor.utils.FakerUtils
import io.github.serpro69.kfaker.Faker
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional
import org.hibernate.exception.ConstraintViolationException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.math.BigDecimal
import kotlin.math.pow

@Service
class DataGeneratorService {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    private val logger: Logger = LoggerFactory.getLogger(DataGeneratorService::class.java)

    @Transactional
    fun generateCustomers(): List<Customer> {
        logger.info("Generating Customers")

        val faker = Faker()

        faker.unique.configuration {
            enable(faker::name)
        }

        val size = 300
        val customers = mutableListOf<Customer>()
        val firstNames = FakerUtils.getFirstNames(size)
        val lastNames = FakerUtils.getLastNames(size)
        val ssns = FakerUtils.getSSNs(size.toDouble().pow(2).toInt())

        for (i in 1..size) {
            for (j in 1..size) {
                val customer = Customer(
                    firstName = firstNames[i - 1],
                    lastName = lastNames[j - 1],
                    age = faker.random.nextInt(18, 90),
                    ssn = ssns[(i - 1) * size + j - 1]
                )

                persistCustomer(customer)

                customers.add(customer)

                if (j % size == 0) {
                    entityManager.flush()
                    entityManager.clear()
                }
            }
        }

        return customers
    }

    private fun persistCustomer(customer: Customer) {
        try {
            entityManager.persist(customer)
        } catch (e: ConstraintViolationException) {
            logger.info("duplicated")
            customer.ssn = FakerUtils.generateSSN()
            persistCustomer(customer)
        }
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
                balance = BigDecimalUtils.randomBetween(
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(100_000))
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
        batchSize: Int
    ) {
        logger.info("Generating Revenues")

        for (i in 1..customers.size) {
            val revenue = Revenue(
                customer = customers[i - 1],
                bankAccount = bankAccounts.find { ba -> ba.customer?.id == customers[i - 1].id },
                amount = BigDecimalUtils.randomBetween(
                    BigDecimal.valueOf(1_000),
                    BigDecimal.valueOf(15_000)
                ),
            )

            entityManager.persist(revenue)

            if (i % batchSize == 0) {
                entityManager.flush()
                entityManager.clear()
            }
        }
    }

    @Transactional
    fun generateLoans(customers: List<Customer>, batchSize: Int) {
        logger.info("Generating Loans")

        val faker = Faker()

        for (i in 1..customers.size) {
            val loanAmount = BigDecimalUtils.randomBetween(
                BigDecimal.valueOf(1_000),
                BigDecimal.valueOf(50_000)
            )

            var payedAmount = BigDecimalUtils.randomBetween(
                BigDecimal.valueOf(1_000),
                BigDecimal.valueOf(50_000)
            )

            while (payedAmount > loanAmount) {
                payedAmount = BigDecimalUtils.randomBetween(
                    BigDecimal.valueOf(1_000),
                    BigDecimal.valueOf(50_000)
                )
            }

            val loan = Loan(
                customer = customers[i - 1],
                loan = loanAmount,
                payed = payedAmount,
                debt = loanAmount - payedAmount,
                delay = faker.random.nextInt(0, 100)
            )

            entityManager.persist(loan)

            if (i % batchSize == 0) {
                entityManager.flush()
                entityManager.clear()
            }
        }
    }

}
