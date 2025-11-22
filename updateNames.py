################################
# USAGE: python .\updateNames.py Better Concrete Conversion
################################
import sys
import os

args = sys.argv[1:]
Name = ' '.join(args)
ModuleName = ''.join(args)
modid = ModuleName.lower()

old_modid = 'timomod'
old_module_name = 'TimoMod'

#Get a list of all file name and directory names in the directory src
Files = []
Directories = []
for dirpath, dirnames, filenames in os.walk('src'):
    for dirname in dirnames:
        Directories.append(os.path.join(dirpath, dirname))
    for filename in filenames:
        if not filename.endswith(('.png')):
            Files.append(os.path.join(dirpath, filename))

Files.append('gradle.properties')

print(Files)
print(Directories)

for file in Files:
    print(file)
    with open(file, 'r', encoding='utf-8') as f:
        filedata = f.read()

    filedata = filedata.replace('"name": "TimoMod",', f'"name": "{Name}",')
    filedata = filedata.replace(old_modid, modid)
    filedata = filedata.replace(old_module_name, ModuleName)

    with open(file, 'w', encoding='utf-8') as f:
        f.write(filedata)

    filename = os.path.basename(file)
    filepath = os.path.dirname(file)

    filename = filename.replace(old_modid, modid)
    filename = filename.replace(old_module_name, ModuleName)

    newFileName = os.path.join(filepath, filename)

    os.rename(file, newFileName)

for directory in sorted(Directories, key=lambda p: p.count(os.sep), reverse=True):
    if old_modid in directory.split('\\')[-1]:
        new_path = directory.replace(old_modid, modid)
        os.rename(directory, new_path)
