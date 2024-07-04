package com.bruno.customerprocessor.batch.tasklet

import com.bruno.customerprocessor.entity.external.Bank
import io.github.serpro69.kfaker.Faker
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.batch.core.Step
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.transaction.PlatformTransactionManager

@Component
class GenerateBanksTasklet() : Tasklet {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        val faker = Faker()

        faker.unique.configuration {
            enable(faker::bank)
        }

        val banks = mutableListOf<Bank>()

        for (i in 1..7) {
            val bank = Bank(
                bankName = faker.bank.name()
            )

            entityManager.persist(bank)

            banks.add(bank)

            entityManager.flush()
            entityManager.clear()
        }

        return RepeatStatus.FINISHED
    }

    @Bean
    fun generateBankDataStep(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager
    ): Step {
        return StepBuilder("Generate Bank Data Step", jobRepository)
            .tasklet(this, transactionManager)
            .build()
    }

}