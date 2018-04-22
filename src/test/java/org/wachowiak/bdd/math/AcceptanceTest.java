package org.wachowiak.bdd.math;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.StoryReporterBuilder;

public abstract class AcceptanceTest extends JUnitStory{

    public AcceptanceTest() {
        Embedder embedder = configuredEmbedder();
        embedder.embedderControls()
                .useStoryTimeouts("30");
    }

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryReporterBuilder(new StoryReporterBuilder().withCodeLocation(CodeLocations.codeLocationFromPath("build/jbehave")));
    }
}
