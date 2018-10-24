package org.elasticsearch.index.analysis;

import com.hankcs.hanlp.seg.CRF.CRFSegment;
import com.hankcs.hanlp.seg.Dijkstra.DijkstraSegment;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.tokenizer.IndexTokenizer;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.SpeedTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import com.hankcs.lucene.HanLPTokenizer;
import org.apache.lucene.analysis.Tokenizer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;

public abstract class HanLPTokenizerFactory extends AbstractTokenizerFactory {

    protected boolean enablePorterStemming;

    private HanLPTokenizerFactory(IndexSettings indexSettings, Environment env, String name, Settings settings) {
        super(indexSettings, name, settings);
        this.enablePorterStemming = settings.getAsBoolean("enablePorterStemming", false);
    }

    public static HanLPTokenizerFactory createStandard(IndexSettings indexSettings, Environment environment, String name, Settings settings) {
        return new HanLPTokenizerFactory(indexSettings, environment, name, settings) {
            @Override
            public Tokenizer create() {
                return new HanLPTokenizer(StandardTokenizer.SEGMENT, null, enablePorterStemming);
            }
        };
    }

    public static HanLPTokenizerFactory createIndex(IndexSettings indexSettings, Environment environment, String name, Settings settings) {
        return new HanLPTokenizerFactory(indexSettings, environment, name, settings) {
            @Override
            public Tokenizer create() {
                return new HanLPTokenizer(IndexTokenizer.SEGMENT, null, enablePorterStemming);
            }
        };
    }

    public static HanLPTokenizerFactory createCRF(IndexSettings indexSettings, Environment environment, String name, Settings settings) {
        return new HanLPTokenizerFactory(indexSettings, environment, name, settings) {
            @Override
            public Tokenizer create() {
                Segment seg = new CRFSegment().enablePartOfSpeechTagging(true);
                return new HanLPTokenizer(seg, null, enablePorterStemming);
            }
        };
    }

}
