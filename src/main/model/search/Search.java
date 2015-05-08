package main.model.search;

import main.model.Model;

/**
 * Created by alex on 5/7/15.
 */
public class Search implements Model
{
    private String keyword;

    public Search(String keyword) { this.keyword = keyword; }

    public String getKeyword() { return keyword; }

    @Override
    public ModelType getModelType() { return ModelType.SEARCH; }
}
