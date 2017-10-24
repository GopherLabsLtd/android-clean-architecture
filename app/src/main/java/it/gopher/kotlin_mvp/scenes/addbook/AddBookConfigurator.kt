package it.gopher.kotlin_mvp.scenes.addbook

import it.gopher.kotlin_mvp.core.usecase.AddBookUseCaseImplementation
import it.gopher.kotlin_mvp.entitygateway.api.BookApiServiceBuilder
import it.gopher.kotlin_mvp.entitygateway.api.gateways.ApiBooksGatewayImplementation

/**
 * This configurator can be replaced using Dagger 2.
 */
public interface AddBookConfigurator {
    fun configure(addBookActivity: AddBookActivity)
}

public class AddBookConfiguratorImplementation(
        val addBookPresenterDelegate: AddBookPresenterDelegate) : AddBookConfigurator {

    override fun configure(addBookActivity: AddBookActivity) {
        val apiClient = BookApiServiceBuilder().build()
        val apiBooksGateway = ApiBooksGatewayImplementation(apiClient)
        val addBookUseCase = AddBookUseCaseImplementation(apiBooksGateway)
        val presenter = AddBookPresenterImplementation(view = addBookActivity, addBookUseCase = addBookUseCase,
                delegate = addBookPresenterDelegate)
        addBookActivity.presenter = presenter
    }

}