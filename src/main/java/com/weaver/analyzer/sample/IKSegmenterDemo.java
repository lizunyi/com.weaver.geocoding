package com.weaver.analyzer.sample;

import com.weaver.analyzer.core.IKSegmenter;
import com.weaver.analyzer.core.Lexeme;

import java.io.StringReader;

/**
 * @Description:
 * @ClassName: IKSegmenterDemo
 * @Author: yanbobo
 * @CreateDate: 2019/6/12 16:50
 */
public class IKSegmenterDemo {

    public static void main(String[] args) {
        try {
            String str = "笨蛋";
            StringReader input = new StringReader(str.trim());
            IKSegmenter ikSeg = new IKSegmenter(input, true);   // true 用智能分词 ，false细粒度
            for (Lexeme lexeme = ikSeg.next(); lexeme != null; lexeme = ikSeg.next()) {
                System.out.println(lexeme.getLexemeText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
