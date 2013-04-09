#awestruct issue: https://github.com/awestruct/awestruct-extensions/issues/14 asking for an official extension
module Awestruct
  module Extensions
    class Maven

      def initialize(maven_project_path)
        @maven_project_path = maven_project_path
      end

      def execute(site)
        maven_tmp_path =  File.join(site.tmp_dir, 'maven', @maven_project_path, 'site')
        project_root_dir = site.dir.to_s.match(/^(.*[^\/?])([\/]?)$/)[1] + @maven_project_path
        if !File.exist? maven_tmp_path
          pom_file = project_root_dir + "/pom.xml"
          cmd = "mvn -f '#{pom_file}' clean site -DsiteOutputDirectory=" + maven_tmp_path
          puts "Executing #{cmd}"
          puts `#{cmd} `
          puts "Maven site geneated for #{@path_prefix}"
        end
        dest = project_root_dir + "/target"
        FileUtils.remove_dir dest #clean any dirty content
        FileUtils.mkpath dest #Create dest path if it doesn't existes
        puts "Getting previous generated maven site from #{maven_tmp_path} to #{dest}"
        FileUtils.cp_r maven_tmp_path, dest #copy awestruct content to project
      end
    end #End Class
  end #End module Extensions
end #End module Awestruct

