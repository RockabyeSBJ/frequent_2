# Path to your project
Set-Location "C:\Users\meanm\Frequent_2"

# Stage all changes
git add .

# Create timestamp
$timestamp = Get-Date -Format "yyyy-MM-dd-HH-mm"

# Commit (allow empty just in case)
git commit -m "Automated backup $timestamp"

# Create a lightweight tag for this backup
git tag "backup-$timestamp"

# Push commit and tag to GitHub
git push origin main --follow-tags