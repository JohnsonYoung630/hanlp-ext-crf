package com.hankcs.lucene;

import java.io.IOException;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.CRF.CRFSegment;
import com.hualongdata.hanlpext.util.DictionaryWatcher;

public class CRFAnalyzer extends Analyzer {

	boolean pstemming;
	public Set<String> filter;

	/**
	 * @param filter
	 *            停用词
	 * @param pstemming
	 *            是否分析词干
	 */
	public CRFAnalyzer(Set<String> filter, boolean pstemming) {
		this.filter = filter;
	}

	/**
	 * @param pstemming
	 *            是否分析词干.进行单复数,时态的转换
	 */
	public CRFAnalyzer(boolean pstemming) {
		this.pstemming = pstemming;
	}

	public CRFAnalyzer() {
		super();
	}

	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		
		Tokenizer tokenizer = new HanLPTokenizer(new CRFSegment().enableCustomDictionaryForcing(true)
				.enableAllNamedEntityRecognize(true)
				.enableIndexMode(true)
				.enableNameRecognize(true)
				, filter, pstemming);
        return new TokenStreamComponents(tokenizer);
//		Tokenizer tokenizer = new HanLPTokenizer(HanLP.newSegment("crf")
//				.enableOffset(true) // 偏移量计算
//				.enableCustomDictionary(true) // 用户词典 (ps:有个强制使用用户词典的，使用户词典的优先级尽可能高） 
//				.enableIndexMode(true) // 索引模式
//				.enableAllNamedEntityRecognize(true), // 全部命名实体识别：中国人名、日本人名、音译人名、地名识别、机构识别
////				.enableMultithreading(true) // 开启多线程
////				.enableNumberQuantifierRecognize(true), // 数词和数量词识别
//				filter, pstemming);
//		return new TokenStreamComponents(tokenizer);
	}

}
