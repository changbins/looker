package com.wenhua.community.util;

/*
 * @Author:ChangBins
 * @Data:2022-09-13  19:19
 * @Description:community-com.wenhua.community.util
 * @Version：1.0
 * @Detail：
 * */

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class SensitiveFilter {

    public static final Logger LOGGER = LoggerFactory.getLogger(SensitiveFilter.class);
    //要替换的符号
    public static final String REPLACEMENT = "***";
    //初始化根节点
    private TrieNode rootNode = new TrieNode();

    /**
     * 初始化方法
     */
    @PostConstruct
    public void init() {
        //读敏感词文件
        try (
                InputStream stream = this.getClass().getClassLoader().getResourceAsStream("sensitive-words.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        ) {
            //读到字符存在以下变量中
            String keyword;
            while ((keyword = reader.readLine()) != null) {
                //添加到前缀树
                this.addKeyword(keyword);
            }
        } catch (IOException e) {
            LOGGER.error("加载敏感词失败" + e.getMessage());
        }

    }

    /**
     * 将一个敏感词加入到前缀树中去
     *
     * @param keyWord 需要加入到前缀树的中的敏感词
     */
    private void addKeyword(String keyWord) {
        TrieNode tempNode = rootNode;
        for (int i = 0; i < keyWord.length(); i++) {
            char c = keyWord.charAt(i);
            TrieNode subNode = tempNode.getSubNode(c);

            if (subNode == null) {
                //初始化子节点
                subNode = new TrieNode();
                tempNode.addSubNodes(c, subNode);
            }
            //让指针指向子节点,进入下一轮循环
            tempNode = subNode;
            //设置结束标识
            if (i == keyWord.length() - 1) {
                tempNode.setKeywordEnd(true);
            }
        }
    }

    /**
     * 过滤敏感词
     *
     * @param text 待过滤的文本
     * @return 过滤完成之后的文本
     */
    public String filter(String text) {
        if (StringUtils.isBlank(text)) {
            return null;
        }
        //指针1
        TrieNode tempNode = rootNode;
        //指定2
        int begin = 0;
        //指针三
        int position = 0;
        //结果
        StringBuilder result = new StringBuilder();

        while (position < text.length()) {
            char c = text.charAt(position);
            //跳过符号
            if (isSymbol(c)){
                if (tempNode == rootNode){
                    result.append(c);
                    begin++;
                }
                position++;
                continue;
            }
            //检查下级节点
            tempNode = tempNode.getSubNode(c);
            if (tempNode == null){
                result.append(text.charAt(begin));
                position = ++begin;
                tempNode = rootNode;
            }else if (tempNode.isKeywordEnd()){
                result.append(REPLACEMENT);
                begin = ++position;
                tempNode = rootNode;
            }else {
                position++;
            }
        }
        //将最后一批余下的字符记入结果
        result.append(text.substring(begin));
        return result.toString();
    }

    /**
     * 判断字符是否是符号
     *
     * @param c 需要验证的字符
     * @return 是否是字符
     */
    private boolean isSymbol(Character c) {
        return !CharUtils.isAsciiAlphanumeric(c) && (c < 0x2E80 || c > 0x9FFF);
    }

    /**
     * 构建前缀树
     */
    private class TrieNode {

        //关键词结束符
        private boolean isKeywordEnd = false;
        //当前节点的子节点(Character是下级字符，TrieNode是下级节点)
        private Map<Character, TrieNode> subNodes = new HashMap<>();

        public boolean isKeywordEnd() {
            return isKeywordEnd;
        }

        public void setKeywordEnd(boolean keywordEnd) {
            isKeywordEnd = keywordEnd;
        }

        /**
         * 添加子节点
         *
         * @param character 下级字符
         * @param trieNode  下级节点
         */
        public void addSubNodes(Character character, TrieNode trieNode) {
            subNodes.put(character, trieNode);
        }

        /**
         * 根据下级字符获取子节点
         *
         * @param character 下级子节点
         * @return 子节点
         */
        public TrieNode getSubNode(Character character) {
            return subNodes.get(character);
        }
    }
}
