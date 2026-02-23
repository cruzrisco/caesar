package caesar

import org.markdownj.MarkdownProcessor

class MarkdownTagLib {
    static defaultEncodeAs = [taglib:'text']
    //static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    def markdown = { attrs, body ->
        out << new MarkdownProcessor().markdown(attrs.md).trim()
    }
}
