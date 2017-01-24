import ammonite.ops._

def menuTail(sts: Vector[String]) = sts.dropWhile((s) => !s.contains("ln-conus")).dropWhile((s) => !s.contains("/table")).tail

def simpleTail(sts: Vector[String]) = sts.dropWhile((s) => !s.contains("<body>")).tail

def hasMenu(sts: Vector[String]) = sts.exists(_.contains("ln-conus"))

def beheaded(sts: Vector[String]) =
  if (hasMenu(sts)) "<center>" +: menuTail(sts) else simpleTail(sts)

def rewrite(file: Path) = {
  val outlines = beheaded(read.lines(file))
  write.over(file, "---\n---\n")
  outlines.foreach((l) => write.append(file, s"$l\n"))
}

def beheadAll(base: Path) =
  ls.rec(base).filter(_.ext == "html").filter((f) => !read.lines(f).head.startsWith("---")).foreach(rewrite)
