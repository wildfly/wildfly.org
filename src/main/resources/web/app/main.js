import './js/mobile-nav';
import './js/guides-search';
import './js/conference';

import hljs from 'highlight.js';
import CopyButtonPlugin from 'highlightjs-copy';

import 'highlightjs-copy/dist/highlightjs-copy.min.css';
import 'highlight.js/styles/github-dark.min.css';

import './main.scss';

hljs.addPlugin(
    new CopyButtonPlugin({
            hook: (text, _) => {
                return text.replaceAll(/\(\d+\)$/gm, '');
            },
        }
    ));
hljs.highlightAll();
