echo "Get current version"
dir=circus.robocalc.robosim.update/target/repository/
remote=/shared/storage/cs/www/robostar/robotool/robosim-metamodel/
url=ahm504@sftp.york.ac.uk
file=$(ls $dir/features | grep -m 1 jar)
version=${file#*_}
version=${version%.jar}
if [[ $version = *[!\ ]* ]]; 
then 
  echo "Current version:" $version;
  rm -rf tmp
  mkdir tmp && cd tmp
  dest=update_$version
  mkdir $dest
  cp -r ../$dir/* $dest
  ln -s $dest update
  rsync -a -e "ssh" -rtzh . $url:$remote
  exit $?;
else
  echo "Couldn't find current version"
  exit 1;
fi
