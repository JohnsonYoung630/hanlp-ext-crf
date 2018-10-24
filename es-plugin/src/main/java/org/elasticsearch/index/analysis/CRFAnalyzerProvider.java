package org.elasticsearch.index.analysis;

import com.hankcs.lucene.CRFAnalyzer;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;

/**
 */
public class CRFAnalyzerProvider extends AbstractIndexAnalyzerProvider<CRFAnalyzer> {

    private final CRFAnalyzer analyzer;

    public CRFAnalyzerProvider(IndexSettings indexSettings, Environment env, @Assisted String name, @Assisted Settings settings) {
        super(indexSettings, name, settings);
        analyzer = new CRFAnalyzer(false);
    }

    public static CRFAnalyzerProvider getIndexAnalyzerProvider(IndexSettings indexSettings, Environment env, String name, Settings settings) {
        return new CRFAnalyzerProvider(indexSettings, env, name, settings);
    }

    @Override
    public CRFAnalyzer get() {
        return this.analyzer;
    }
    

}
