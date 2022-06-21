echo "Get current version"
dir=circus.robocalc.robosim.update/target/repository/
remote=${ROBOSTAR_WEB_ROOT}/robotool/robosim-metamodel/
url=${ROBOSTAR_WEB_USER}@${ROBOSTAR_WEB_HOST}
file=$(ls $dir/features | grep -m 1 jar)
version=${file#*_}
version=${version%.jar}
BRANCH_NAME=${GITHUB_REF##*/}

# Use the branch name to choose the name of the branch. This assumes
# no branch of name 'update' will ever be used.
if [[ $BRANCH_NAME = master ]];
then
  update=update
else
  update=$BRANCH_NAME
fi

if [[ $version = *[!\ ]* ]]; 
then 
  echo "Current version:" $version;
  echo "Branch:" $BRANCH_NAME;
  dest=${update}_${version}
  echo "Target dir:" $dest;
  rm -rf tmp
  mkdir tmp && cd tmp
  mkdir $dest
  cp -r ../$dir/* $dest
  
  # Deploy a copy of the ECore file as well
  cp ../circus.robocalc.robosim/model/robosim.ecore $dest

  # In the new host, it is not possible to generate a symlink that points to
  # a non-existent target, such as 'update', before it is actually created.
  # So here we first transfer the update folder, then create the symlink and
  # finally transfer that too.
  rsync -a -e "ssh" -rtzh . $url:$remote
  ln -s $dest ${update}
  rsync -a -e "ssh" -rtzh . $url:$remote
  exit $?;
else
  echo "Couldn't find current version"
  exit 1;
fi
