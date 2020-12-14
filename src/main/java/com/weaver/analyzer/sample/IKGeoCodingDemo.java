/**
 * IK 中文分词  版本 5.0.1
 * IK Analyzer release 5.0.1
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * 源代码由林良益(linliangyi2005@gmail.com)提供
 * 版权声明 2012，乌龙茶工作室
 * provided by Linliangyi and copyright 2012 by Oolong studio
 * 
 * 
 */
package com.weaver.analyzer.sample;

import com.weaver.analyzer.lucene.IKAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.apache.lucene.util.ArrayUtil;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 使用IKAnalyzer进行分词的演示
 * 2012-10-22
 *
 */
public class IKGeoCodingDemo {
  private static final  Analyzer analyzer = new IKAnalyzer(false);

  public static void main(String[] args) throws IOException {
    List<String> words = getTokenStream("丰台区丰台南路刘家村193号");
    System.out.println(words.stream().collect(Collectors.joining(",")));
    List<String> words1 = getTokenStream("刘家村193号（近丰台医院）");
    System.out.println(words1.stream().collect(Collectors.joining(",")));
  }

  private static List<String> getTokenStream(String str) throws IOException {
    List<String> result = new ArrayList<>();
    TokenStream tokenStream = analyzer.tokenStream("index", new StringReader(str));
    tokenStream.reset();
    // 获取词元文本属性
    CharTermAttribute term = tokenStream.addAttribute(CharTermAttribute.class);
    while (tokenStream.incrementToken()) {
      result.add(term.toString());
    }
    tokenStream.end();
    tokenStream.close();
    return result;
  }
}
