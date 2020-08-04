# Wildfly.org Website Based on Jekyll

## Getting Started

These instructions will get you a copy of the wildfly.org website up and running on your local machine for development purposes.

### Installation
[Jekyll static site generator documentation](https://jekyllrb.com/docs/).

1. Install a full [Ruby development environment](https://jekyllrb.com/docs/installation/#requirements).
2. Install [Bundler](https://jekyllrb.com/docs/ruby-101/#bundler):
  
        gem install bundler

3. Fork the [project repository](https://github.com/wildfly/wildfly.org), then clone your fork:
  
        git clone git@github.com:YOUR_USER_NAME/wildfly.org.git

4. Change into the project directory:
  
        cd wildfly.org

5. Use bundler to fetch all required gems in their respective versions:

        bundle install

6. Build the site and make it available on a local server:
  
        bundle exec jekyll serve
        
7. Now browse to http://localhost:4000

> If you encounter any unexpected errors during the above, please refer to the [troubleshooting](https://jekyllrb.com/docs/troubleshooting/#configuration-problems) page or the [requirements](https://jekyllrb.com/docs/installation/#requirements) page, as you might be missing development headers or other prerequisites.


**For more regarding the use of Jekyll, please refer to the [Jekyll Step by Step Tutorial](https://jekyllrb.com/docs/step-by-step/01-setup/).**

## Writing a News post

To write a News post:

- If you are a new author, create an author entry in [_data/authors.yaml](https://github.com/wildfly/wildfly.org/tree/master/_data/authors.yaml)
    - `avatar` is used the name of the author's picture in [this directory](https://github.com/wildfly/wildfly.org/tree/master/assets/img/authors)
- Create an news entry under [_posts](https://github.com/wildfly/wildfly.org/tree/master/_posts)
    - The file name is `yyyy-mm-dd-slug.adoc`
- All news posts should be written in [AsciiDoc format](https://asciidoctor.org/docs/asciidoc-syntax-quick-reference/).
- Create a pull request against the master branch.

## Contributing

Please read [CONTRIBUTING.md](https://github.com/wildfly/wildfly.org/tree/master/CONTRIBUTING.md) for details on the process for submitting pull requests to us.

**Important:** the guides are maintained in the wildfly.github.io repository and pull requests should be submitted there:
https://github.com/wildfly/wildfly.github.io

## License

This website is licensed under the [Creative Commons Attribution 3.0](https://creativecommons.org/licenses/by/3.0/).