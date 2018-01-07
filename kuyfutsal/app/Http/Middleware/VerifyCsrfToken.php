<?php

namespace App\Http\Middleware;

use Illuminate\Foundation\Http\Middleware\VerifyCsrfToken as Middleware;

class VerifyCsrfToken extends Middleware
{
    /**
     * The URIs that should be excluded from CSRF verification.
     *
     * @var array
     */
    protected $except = [
        //
    ];
   // 
   //  public function handle($request, \Closure $next)
   // {
   //     if ($this->isReading($request)
   //         || $this->excludedRoutes($request)
   //         || $this->tokensMatch($request))
   //     {
   //         return $this->addCookieToResponse($request, $next($request));
   //     }
   //
   //     throw new \TokenMismatchException;
   // }
   //
   // /**
   //  * This will return a bool value based on route checking.
   //
   //  * @param  Request $request
   //  * @return boolean
   //  */
   // protected function excludedRoutes($request)
   // {
   //     foreach($this->routes as $route)
   //         if ($request->is($route))
   //             return true;
   //
   //                  return false;
   // }

}
