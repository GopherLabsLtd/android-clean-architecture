package it.gopher.kotlin_mvp.scenes.addbook

/**
 * This configurator can be replaced using Dagger 2.
 */
public interface AddBookConfigurator {
    fun configure(addBookActivity: AddBookActivity)
}

public class AddBookConfiuguratorImplementation : AddBookConfigurator {
    override fun configure(addBookActivity: AddBookActivity) {
        // TODO :
    }

}