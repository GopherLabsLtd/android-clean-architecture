package it.gopher.kotlin_mvp.scenes.books

import it.gopher.kotlin_mvp.core.usecase.DeleteBookUseCaseImplementation
import it.gopher.kotlin_mvp.core.usecase.DisplayBookImplementation
import it.gopher.kotlin_mvp.entitygateway.api.BookApiServiceBuilder
import it.gopher.kotlin_mvp.entitygateway.api.gateways.ApiBooksGatewayImplementation

public interface BooksConfigurator {
    fun configure(booksActivity: BooksActivity)
}

public class BooksConfiguratorImplementation : BooksConfigurator {
    override fun configure(booksActivity: BooksActivity) {
        val apiClient = BookApiServiceBuilder().build()
        val apiBooksGateway = ApiBooksGatewayImplementation(apiClient)
        val displayBookUseCase = DisplayBookImplementation(apiBooksGateway)
        val deleteBookUseCase = DeleteBookUseCaseImplementation(apiBooksGateway)
        val router = BooksViewRouterImplementation(booksActivity)
        val presenter = BooksPresenterImplementation(
                booksActivity,
                displayBookUseCase,
                deleteBookUseCase,
                router
        )
        booksActivity.presenter = presenter
    }
}