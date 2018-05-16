package org.wachowiak.bdd.e2e.stories;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.UnderscoredCamelCaseResolver;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.wachowiak.bdd.e2e.stories.configuration.E2EConfiguration;

public class Math extends JUnitStory{

    // Here we specify the configuration, starting from default MostUsefulConfiguration, and changing only what is needed
    @Override
    public Configuration configuration() {

        return new MostUsefulConfiguration()
                // where to find the stories
                .useStoryPathResolver(new UnderscoredCamelCaseResolver())
                // CONSOLE and TXT reporting
                .useStoryReporterBuilder(new StoryReporterBuilder().withDefaultFormats().withFormats(Format.CONSOLE, Format.HTML));
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        // varargs, can have more that one steps classes
        return new SpringStepsFactory(configuration(),
                new AnnotationConfigApplicationContext(E2EConfiguration.class));
    }
}
