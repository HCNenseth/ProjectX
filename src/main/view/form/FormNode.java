package main.view.form;

/**
 * Created by alex on 4/20/15.
 */
public class FormNode
{
    private String key;
    private String value;
    private String error;
    private String regex;
    private boolean required;

    public static class Builder
    {
        private String key;
        private String value = "";
        private String error = "";
        private String regex = "";
        private boolean required = true;

        public Builder(String key)
        {
            this.key = key;
        }

        public Builder value(String value)
        {
            this.value = value; return this;
        }

        public Builder error(String error)
        {
            this.error = error; return this;
        }

        public Builder regex(String regex)
        {
            this.regex = regex; return this;
        }

        public Builder required(boolean value)
        {
            this.required = value; return this;
        }

        public FormNode build()
        {
            return new FormNode(this);
        }
    }

    private FormNode(Builder builder)
    {
        this.key = builder.key;
        this.value = builder.value;
        this.error = builder.error;
        this.regex = builder.regex;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getError()
    {
        return error;
    }

    public void setError(String error)
    {
        this.error = error;
    }

    public String getRegex()
    {
        return regex;
    }

    public void setRegex(String regex)
    {
        this.regex = regex;
    }
}
