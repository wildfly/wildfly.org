# WildFly Website Based on Roq

This repository provides the content and site build instructions for the https://wildfly.org website.

The project content is primarily maintained in markdown and asciidoc files, with Quarkus Roq used to generate the HTML content. The site is published using GitHub Pages, with the Quarkus Roq GitHub Action triggered to render the site whenever the `main` branch is updated.

The Quarkus maven plugin can be used to generate a local copy of the site for development purposes.

For more on Roq, see the [Quarkus Roq static site generator documentation](https://iamroq.com/).

## Repository Structure

The [Roq the basics](https://iamroq.com/docs/basics/) Roq documentation page nicely explains the general repository structure for a Roq site. This repository follows that structure with a couple of twists:

- The [content/guides](https://github.com/wildfly/wildfly.org/tree/main/content/guildes) directory contains the main content for the guides listed on the https://wildfly.org/guides page.
- Roq is extensible, and the [src](https://github.com/wildfly/wildfly.org/tree/main/src) tree includes Java code used for that.

## Contributing

Please read [CONTRIBUTING.md](https://github.com/wildfly/wildfly.org/blob/master/contribute.md) for details on how to build the site and contribute changes.

## License

This website is licensed under the [Creative Commons Attribution 3.0](https://creativecommons.org/licenses/by/3.0/) license.
