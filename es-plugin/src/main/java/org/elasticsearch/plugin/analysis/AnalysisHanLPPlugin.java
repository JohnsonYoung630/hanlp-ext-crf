package org.elasticsearch.plugin.analysis;

import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.index.analysis.*;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

import com.hankcs.hanlp.HanLP;
import com.hualongdata.hanlpext.util.DictionaryWatcher;

import org.elasticsearch.index.analysis.CRFAnalyzerProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AnalysisHanLPPlugin extends Plugin implements AnalysisPlugin {
	
	
    public AnalysisHanLPPlugin() {
		super();
		HanLP.Config.Normalization = true;
		try {
			new DictionaryWatcher().customDictWatcher();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
    public Map<String, AnalysisModule.AnalysisProvider<TokenizerFactory>> getTokenizers() {
        HashMap<String, AnalysisModule.AnalysisProvider<TokenizerFactory>> tokenizers = new HashMap<>();
        tokenizers.put("hanlp", HanLPTokenizerFactory::createStandard);
        tokenizers.put("hanlp-index", HanLPIndexAnalyzerFactory::new);
        tokenizers.put("hanlp-crf", HanLPTokenizerFactory::createCRF);
        return tokenizers;
    }

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> getAnalyzers() {
        Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> analyzers = new HashMap<>();
        analyzers.put("hanlp", HanLPAnalyzerProvider::new);
        analyzers.put("hanlp-index", HanLPIndexAnalyzerProvider::new);
        analyzers.put("hanlp-crf", CRFAnalyzerProvider::new);
        return analyzers;
    }
    


}